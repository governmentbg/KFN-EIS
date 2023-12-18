import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Popper from '@mui/material/Popper';
import Typography from '@mui/material/Typography';
import { GridRenderCellParams } from '@mui/x-data-grid';
import { memo, useEffect, useRef, useState } from 'react';

interface GridCellExpandProps {
  value: string;
  width: number;
}

const isOverflown = (element: Element): boolean => {
  return (
    element.scrollHeight > element.clientHeight ||
    element.scrollWidth > element.clientWidth
  );
};

const GridCellExpand = memo(function GridCellExpand(
  props: GridCellExpandProps
) {
  const { width, value } = props;
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
        }}
      >
        {value}
      </Box>
      {showPopper && (
        <Popper
          open={showFullCell && anchorEl !== null}
          anchorEl={anchorEl}
          style={{ width: 'fit-content', maxWidth: '20rem' }}
        >
          <Paper elevation={5} style={{ maxHeight: '10rem', overflow: 'auto' }}>
            <Typography variant="body2" p={'1rem'}>
              {value}
            </Typography>
          </Paper>
        </Popper>
      )}
    </Box>
  );
});

const RenderCellExpand = (params: GridRenderCellParams<string>) => {
  const {
    value,
    colDef: { computedWidth },
  } = params;

  return (
    <GridCellExpand
      value={
        !isNaN(Number(value))
          ? Number(value).toFixed(5).toString() ?? ''
          : value?.toString() ?? ''
      }
      width={computedWidth}
    />
  );
};

export default RenderCellExpand;
