import React from 'react';
import Logos from './logos.png';

const Logo = (props) => {
  return (
    <img
      alt="Logo"
      src={Logos}
      width="180" height="50"
      {...props}
    />
  );
};

export default Logo;
