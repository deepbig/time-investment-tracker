import React from 'react';
import {
  Box,
  Container,
  makeStyles
} from '@material-ui/core';
import Page from '../components/styles/Page';
import Postings from '../components/posting/Postings';

const useStyles = makeStyles((theme) => ({
  root: {
    backgroundColor: theme.palette.background.dark,
    height: 'calc(100% +256px)',
    paddingBottom: theme.spacing(3),
    paddingTop: theme.spacing(3)
  }
}));

const PostingPage = () => {
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
        <Container maxWidth="lg">
          <Postings />
        </Container>
      </Box>
    </Page>
  );
};

export default PostingPage;
