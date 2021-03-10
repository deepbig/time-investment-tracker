import React, { useEffect, useState } from 'react';
import './LoadingLogo.css';


const LoadingLogo = () => {
  const [start, setStart] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setStart(false);
    }, 1500);
    return () => clearTimeout(timer);
  }, []);

  return (
    start === true ?
    <div class="fullScreen">
      <div class="dimScreen">
        <img src="/LoadingLogo.gif" alt="Logo" class="center" />
      </div>
    </div>
      : null
  )
};

export default LoadingLogo;