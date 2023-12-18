import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Popper from '@mui/material/Popper';
import Typography, { TypographyProps } from '@mui/material/Typography';
import { GridRenderCellParams } from '@mui/x-data-grid';
import { memo, useEffect, useRef, useState } from 'react';

interface GridCellExpandProps {
  value: string;
  width: number;
}

type GridRenderCellAdditionalParams<C extends React.ElementType> = {
  typographyProps?: TypographyProps<C, { component?: C }>;
};

type GridCellExpandExtendedProps = GridCellExpandProps &
  GridRenderCellAdditionalParams<'span'>;

const isOverflown = (element: Element): boolean => {
  return (
    element.scrollHeight > element.clientHeight ||
    element.scrollWidth > element.clientWidth
  );
};

const GridCellExpand = memo(function GridCellExpand(
  props: GridCellExpandExtendedProps
) {
  const { width, value, typographyProps } = props;
  const wrapper = useRef<HTMLDivElement | null>(null);
  const cellDiv = useRef(null);
  const cellValue = useRef(null);
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const [showFullCell, setShowFullCell] = useState(false);
  const [showPopper, setShowPopper] = useState(false);

  const handleMouseEnter = () => {
    const isCurrentlyOverflown = isOverflown(cellValue.current!);
    setShowPopper(isCurrentlyOverflown);
    setAnchorEl(cellDiv.current);
    setShowFullCell(true);
  };

  const handleMouseLeave = () => {
    setShowFullCell(false);
  };

  useEffect(() => {
    if (!showFullCell) return;

    const handleKeyDown = (nativeEvent: KeyboardEvent) => {
      if (nativeEvent.key === 'Escape' || nativeEvent.key === 'Esc') {
        setShowFullCell(false);
      }
    };

    document.addEventListener('keydown', handleKeyDown);

    return () => {
      document.removeEventListener('keydown', handleKeyDown);
    };
  }, [setShowFullCell, showFullCell]);

  return (
    <Box
      ref={wrapper}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
      sx={{
        alignItems: 'center',
        lineHeight: '1.5rem',
        width: '100%',
        height: '100%',
        position: 'relative',
        display: 'flex',
      }}
    >
      <Box
        ref={cellDiv}
        sx={{
          height: '100%',
          width,
          display: 'block',
          position: 'absolute',
          top: 0,
        }}
      />

      <Box
        ref={cellValue}
        sx={{
          whiteSpace: 'nowrap',
          overflow: 'hidden',
          textOverflow: 'ellipsis',
          width: '100%',
          position: 'relative',
        }}
      >
        {
          <Typography variant="body2" p={'1rem'} {...typographyProps}>
            {value}
          </Typography>
        }
      </Box>

      {showPopper && (
        <Popper
          placement="top-start"
          open={showFullCell && anchorEl !== null}
          anchorEl={anchorEl}
          sx={{ width: 'fit-content', maxWidth: '20rem' }}
        >
          <Paper
            elevation={5}
            sx={{ maxHeight: '10rem', overflow: 'auto', p: '.5rem' }}
          >
            {
              <Typography variant="body2" {...typographyProps}>
                {value}
              </Typography>
            }
          </Paper>
        </Popper>
      )}
    </Box>
  );
});

const RenderCellExpand = <C extends React.ElementType>(
  params: GridRenderCellParams<string>,
  additionalParams?: GridRenderCellAdditionalParams<C>
) => {
  const { value, colDef } = params;
  const { typographyProps } = additionalParams || {};

  return (
    <GridCellExpand
      value={value || ''}
      width={colDef.computedWidth}
      {...(typographyProps && { typographyProps })}
    />
  );
};

export default RenderCellExpand;
