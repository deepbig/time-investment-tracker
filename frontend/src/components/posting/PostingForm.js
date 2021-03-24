import React from 'react';
import { useLocation } from 'react-router-dom';
import {
  Grid,
  makeStyles,
} from '@material-ui/core';
import Activity from '../activity/Activity';
import Result from '../result/Result';
import AddForm from '../addForm/AddForm';


const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(2),
  },
}));

const PostingForm = ({ type }) => {
  const classes = useStyles();
  const location = useLocation();

  return (
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <AddForm type={type} />
      </Grid>
      <Grid item xs={12}>
        {type === "activity" ? <Activity /> : <Result />}
      </Grid>
    </Grid>
  );
};

export default PostingForm;
