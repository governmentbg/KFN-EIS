import { useState, useEffect, useRef } from 'react';

export const useOuterClick = (cb?: Function) => {
  const [isClickedOutside, setIsClickedOutside] = useState(false);
  const ref: any = useRef(null);

  const handleClickOutside = (event: any) => {
    if (ref.current && !ref.current.contains(event.target)) {
      cb && cb();
    }
  };

  useEffect(() => {
    document.addEventListener('click', handleClickOutside, true);
    return () => {
      document.removeEventListener('click', handleClickOutside, true);
    };
  });

  return { ref, isClickedOutside, setIsClickedOutside };
};

export default useOuterClick;
