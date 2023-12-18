import Accordion, { AccordionProps } from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import AccordionDetails from '@mui/material/AccordionDetails';
import Box from '@mui/material/Box';
import AccordionHeader from './AccordionHeader';
import { useEffect, useRef, useState } from 'react';

type RegisterAccordionPropsType = {
  accordionTitle: string;
  accordionContent: React.ReactNode;
  accordionProps?: Omit<AccordionProps, 'children'>;
};
const RegisterAccordion = ({
  accordionTitle,
  accordionContent,
  accordionProps,
}: RegisterAccordionPropsType) => {
  const [isChildrenMounted, setIsChildrenMounted] = useState<boolean>(false);
  const [expanded, setExpanded] = useState<boolean>(false);

  expanded && !isChildrenMounted && setIsChildrenMounted(true);

  return (
    <Accordion
      {...accordionProps}
      sx={{ width: '100%' }}
      onChange={(event, isExpanded) => {
        setExpanded(isExpanded);
      }}
    >
      <AccordionSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1a-content"
        id="panel1a-header"
        sx={{
          backgroundColor: '#eff6fe',
        }}
      >
        <AccordionHeader>{accordionTitle}</AccordionHeader>
      </AccordionSummary>

      {isChildrenMounted && (
        <AccordionDetails>
          <Box
            sx={{
              display: 'flex',
              flexWrap: 'wrap',
            }}
          >
            {accordionContent}
          </Box>
        </AccordionDetails>
      )}
    </Accordion>
  );
};

export default RegisterAccordion;
