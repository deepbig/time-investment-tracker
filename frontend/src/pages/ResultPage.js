import React from 'react';
import {
  Box,
  Container,
  makeStyles
} from '@material-ui/core';
import Page from '../components/styles/Page';
import PostingForm from '../components/posting/PostingForm';

const useStyles = makeStyles((theme) => ({
  root: {
    backgroundColor: theme.palette.background.dark,
    height: 'calc(100% +256px)',
    paddingBottom: theme.spacing(3),
    paddingTop: theme.spacing(3)
  }
}));

const ResultPage = () => {
  const classes = useStyles();

  return (
    <Page
      className={classes.root}
      title="Result"
    >
      <Box
        display="flex"
        flexDirection="column"
        height="100%"
        justifyContent="center"
      >
        {/* <Container maxWidth="lg"> */}
        <Container maxWidth={false}>
          <PostingForm type="result" />
        </Container>
      </Box>
    </Page>
  );
};

export default ResultPage;
