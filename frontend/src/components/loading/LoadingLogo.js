import React, { useEffect, useState } from 'react';
import {
  Backdrop,
  makeStyles
} from '@material-ui/core';
import './LoadingLogo.css';


const useStyles = makeStyles((theme) => ({
  backdrop: {
    zIndex: theme.zIndex.drawer + 1000
  },
}));

const LoadingLogo = () => {
  const classes = useStyles();
  const [start, setStart] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setStart(false);
    }, 1500);
    return () => clearTimeout(timer);
  }, []);

  return (
    start === true ?
    <Backdrop className={classes.backdrop}>
    <div class="fullScreen">
      <div class="dimScreen">
        <img src="/LoadingLogo.gif" alt="Logo" class="center" />
      </div>
    </div>

    </Backdrop>
      : null
  )
};

export default LoadingLogo;