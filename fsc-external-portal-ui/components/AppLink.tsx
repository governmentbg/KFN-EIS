/* eslint-disable react/display-name */
/* eslint-disable import/no-anonymous-default-export */
import React from 'react';
import Link, { LinkProps } from 'next/link';
import { useRouter } from 'next/router';

interface Props extends LinkProps {
  children: JSX.Element;
  activeClassName?: string;
  className?: string;
  active?: boolean;
}

export default ({
  href,
  children,
  className,
  activeClassName,
  active,
  ...restProps
}: Props) => (
  <Link href={href} {...restProps} passHref>
    {React.cloneElement(children, {
      className:
        useRouter().pathname === href || active ? activeClassName : className,
    })}
  </Link>
);
