import React from 'react';
import {
  Box,
  Container,
  makeStyles
} from '@material-ui/core';
import Page from '../components/styles/Page';
import DashboardForm from '../components/dashboard/DashboardForm';

const useStyles = makeStyles((theme) => ({
  root: {
    backgroundColor: theme.palette.background.dark,
    height: 'calc(100% +256px)',
    paddingBottom: theme.spacing(3),
    paddingTop: theme.spacing(3)
  }
}));

const DashboardPage = () => {
  const classes = useStyles();
  
  return (
    <Page
      className={classes.root}
      title="Dashboard"
    >
      <Box
        display="flex"
        flexDirection="column"
        height="100%"
        justifyContent="center"
      >
        {/* <Container maxWidth="lg"> */}
        <Container maxWidth={false}>
          <DashboardForm />
        </Container>
      </Box>
    </Page>
  );
};

export default DashboardPage;
