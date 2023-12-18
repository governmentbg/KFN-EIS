import React from 'react';
import Stepper, { StepperProps } from '@mui/material/Stepper';
import Step, { StepProps } from '@mui/material/Step';
import StepLabel, { StepLabelProps } from '@mui/material/StepLabel';
import Stack, { StackProps } from '@mui/material/Stack';
import { useTheme } from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';

type LinearStepperProps = {
  steps: string[];
  activeStep: number;
  rootProps?: StackProps;
  stepperProps?: StepperProps;
  stepProps?: StepProps;
  stepLabelProps?: StepLabelProps;
};

export default function LinearStepper(props: LinearStepperProps) {
  const {
    steps,
    activeStep,
    rootProps,
    stepperProps,
    stepProps,
    stepLabelProps,
  } = props || {};
  const theme = useTheme();
  const xs = useMediaQuery(theme.breakpoints.down('sm'));
  return (
    <Stack {...rootProps}>
      <Stepper
        activeStep={activeStep - 1}
        orientation={xs ? 'vertical' : 'horizontal'}
        {...stepperProps}
      >
        {steps.map((label: string) => {
          const labelProps: Partial<StepLabelProps> = { tabIndex: 0 };
          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...stepLabelProps} {...labelProps}>
                {label}
              </StepLabel>
            </Step>
          );
        })}
      </Stepper>
    </Stack>
  );
}
