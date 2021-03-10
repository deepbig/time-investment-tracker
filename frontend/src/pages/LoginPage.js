import React from 'react';
import {
  makeStyles
} from '@material-ui/core';
import Page from '../components/styles/Page';
import LoginForm from '../components/auth/LoginForm';

const useStyles = makeStyles((theme) => ({
  root: {
    // backgroundColor: theme.palette.background.dark,
    backgroundColor: theme.palette.background.dark,
    height: '100%',
    paddingBottom: theme.spacing(3),
    paddingTop: theme.spacing(3)
  }
}));

const LoginPage = () => {
  const classes = useStyles();

  return (
    <Page
      className={classes.root}
      title="Login"
    >
      <LoginForm />
    </Page>
  );
};

export default LoginPage;
