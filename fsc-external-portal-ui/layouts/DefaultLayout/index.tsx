import React, { ReactNode, useCallback, useEffect, useState } from 'react';
import makeStyles from '@mui/styles/makeStyles';
import { Theme } from '@mui/material/styles';
import clsx from 'clsx';
import Header from './Header';

const useStyles = makeStyles((theme: Theme) => ({
  hidden: {
    visibility: 'hidden',
  },
  visible: {
    visibility: 'visible',
    display: 'flex',
    flexDirection: 'column',
    flex: 1,
    height: '100vh',
  },
  main: {
    display: 'flex',
    flexDirection: 'column',
    flex: 1,
    justifyContent: 'center',
    alignContent: 'center',
    alignItems: 'center',
  },
}));

const DefaultLayout = ({ children }: { children: ReactNode }) => {
  //hack for FOUC. Prevents flickering for client only rendered stuff in the interim.
  const [mounted, setMounted] = useState(false);
  const [headerHeight, setHeaderHeight] = useState<string | undefined>(
    '9.375rem'
  );
  const styles = useStyles();

  const setHeaderSize = useCallback(() => {
    const headerComponent = document.getElementById('header');
    const headerHeight = headerComponent?.offsetHeight
      ? headerComponent?.offsetHeight + 'px'
      : '9.375rem';
    setHeaderHeight(headerHeight);
  }, []);

  useEffect(() => setMounted(true), []);
  useEffect(() => {
    setHeaderSize();

    window.addEventListener('resize', setHeaderSize);
    return () => window.removeEventListener('resize', setHeaderSize);
  }, [setHeaderSize]);

  return (
    <div className={clsx(!mounted ? styles.hidden : styles.visible)}>
      <Header />
      <main
        className={styles.main}
        style={{
          height: 'calc(100vh - ' + headerHeight + ')',
          marginTop: headerHeight,
        }}
      >
        {children}
      </main>
    </div>
  );
};

export default DefaultLayout;
