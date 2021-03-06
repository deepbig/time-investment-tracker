import React from 'react';
import {
  Grid,
} from '@material-ui/core';
import Activity from '../activity/Activity';
import Result from '../result/Result';
import AddForm from '../addForm/AddForm';


const PostingForm = ({ type }) => {

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
