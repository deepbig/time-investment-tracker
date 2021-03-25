import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  Card,
  CardContent,
  Grid,
  Typography,
  colors,
  makeStyles,
  TextField,
  IconButton,
  Divider,
} from '@material-ui/core';
import { CategoryList } from '../../lib/api/category';
import Autocomplete from '@material-ui/lab/Autocomplete';
import AddCircleIcon from '@material-ui/icons/AddCircle';

const useStyles = makeStyles((theme) => ({
  root: {
    height: '100%'
  },
  avatar: {
    backgroundColor: colors.grey[600],
    height: 56,
    width: 56
  },
  differenceIcon: {
    color: colors.red[900]
  },
  differenceValue: {
    color: colors.red[900],
    marginRight: theme.spacing(1)
  }
}));

/**
 * Add New Activity:
 * Category name (selection, + button)
 * Icon avata random imag + multiline text field
 * divider (if needed)
 * image, gif, poll, emoji, schedule, practive count, duration, Post button
 */
const AddForm = ({ type }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [value, setValue] = useState('');

  const { category, activityAddForm, resultAddForm } = useSelector(({ category, activity, result }) => ({
    category: category,
    activity: activity.activityAddForm,
    result: result.activityAddForm,
  }));

  useEffect(() => {
    //get all category list.
    CategoryList(dispatch);
    console.log("category list started", category.categoryList);
  }, [dispatch]);

  const handleChangeValue = (event) => {
    setValue(event.target.value);
  }
  

  return (
    <Card>
      <CardContent>
        <Typography align="center" variant="h3" color="textPrimary" component="h3">
          New Activity
        </Typography>
        <Divider />
        <Grid
          container
          justify="flex-start"
          alignItems="flex-end"
        >
          {console.log(value)}
            <Autocomplete
              id="category-combo-box"
              options={category.categoryList.list !== undefined ? category.categoryList.list : []}
              getOptionLabel={(option) => option.category_name}
              style={{ width: 150 }}
              onChange={handleChangeValue}
              renderInput={(params) => <TextField {...params} label="Category" margin="dense" />}
            />
            <IconButton>
              <AddCircleIcon />
            </IconButton>
        </Grid>

        <TextField
          id="activity-context-full-width"
          // style={{ margin: 8 }}
          placeholder="What activity did you do?"
          fullWidth
          multiline
          margin="normal"
          inputProps={{ maxLength: 255 }}
        />

        {/* divider */}
        {/* activity count & duration + save button on the left */}

      </CardContent>
    </Card>
  );
};

export default AddForm;