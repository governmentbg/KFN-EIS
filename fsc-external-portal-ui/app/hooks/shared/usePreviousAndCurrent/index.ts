import { useEffect, useRef, useState } from 'react';

const usePreviousAndCurrent = <T>(value: T): [T | undefined, T] => {
  const previousValueRef = useRef<T>();
  const [currentValue, setCurrentValue] = useState(value);

  useEffect(() => {
    previousValueRef.current = currentValue;
    setCurrentValue(value);
  }, [currentValue, value]);

  return [previousValueRef.current, currentValue];
};

export default usePreviousAndCurrent;
