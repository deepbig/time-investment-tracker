import React, { useEffect, useState } from 'react';
import clsx from 'clsx';
import { useDispatch, useSelector } from 'react-redux';
import {
  Card,
  CardContent,
  CardActions,
  Grid,
  Typography,
  colors,
  makeStyles,
  TextField,
  Divider,
  FormControl,
  Input,
  InputLabel,
  FormHelperText,
  InputAdornment,
  Button
} from '@material-ui/core';
import { changeField, initializeForm } from '../../modules/addForm';
import { CreateActivity, CreateResult } from '../../lib/api/addForm';
import { CategoryList } from '../../lib/api/category';
import CategoryDialog from './CategoryDialog';
import Autocomplete from '@material-ui/lab/Autocomplete';
import Alert from '../../lib/alert';

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
  },
  margin: {
    margin: theme.spacing(1),
  },
  textFieldAdornment: {
    width: '25ch'
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
  const [categorySelected, setCategorySelected] = useState({});

  const { category, activity_add_form, result_add_form } = useSelector(({ category, addForm }) => ({
    category: category,
    activity_add_form: addForm.activity_add_form,
    result_add_form: addForm.result_add_form
  }));

  useEffect(() => {
    //get all category list.
    CategoryList(dispatch);
    if (type === "activity") {
      dispatch(initializeForm('activity_add_form'));
    } else if (type === "result") {
      dispatch(initializeForm('result_add_form'));
    }
  }, [dispatch, type]);


  const handleChange = (event) => {
    const { name, value } = event.target;

    if ((type === "activity" || type === "result")) {
      if (name === "content") {
        dispatch(
          changeField({
            form: `${type}_add_form`,
            key: name,
            value: value,
          })
        );
      } else {
        dispatch(
          changeField({
            form: `${type}_add_form`,
            key: name,
            value: isNaN(parseInt(value)) ? parseInt(value) : value,
          })
        );
      }
    }
  }

  const handleSave = (event) => {
    event.target.blur();
    if (categorySelected.category_name !== undefined && categorySelected.category_name !== null) {

      if (type === "activity") {
        CreateActivity(dispatch, activity_add_form, categorySelected, setCategorySelected);
      } else if (type === "result") {
        CreateResult(dispatch, result_add_form, categorySelected, setCategorySelected);
      } else {
        Alert(0, "Invalid type. Given type is nether activity nor result.", 'Okay', null, null);
      }
    }
    else {
      Alert(0, "Category cannot be empty. Please select a category to continue.", 'Okay', null, null);
    }

  }

  return (
    <Card>
      <CardContent>
        <Typography align="center" variant="h3" color="textPrimary" component="h3">
          New {type === "activity" ? "Activity" : "Result"}
        </Typography>
        <Divider />
        <Grid
          container
          justify="flex-start"
          alignItems="flex-end"
        >
          <Autocomplete
            id="category_name"
            name="category_name"
            onChange={(event, newValue) => {
              setCategorySelected(newValue);
            }}
            options={category.categoryList.list !== undefined ? category.categoryList.list : []}
            getOptionLabel={(option) => option.category_name}
            style={{ width: 150 }}
            renderInput={(params) => <TextField {...params} label="Category" margin="dense" />}
          />

          <CategoryDialog />
        </Grid>

        <TextField
          id="activity-context-full-width"
          name="content"
          // style={{ margin: 8 }}
          placeholder="What activity did you do?"
          fullWidth
          multiline
          margin="normal"
          value={type === "activity" ? activity_add_form.content : result_add_form.content}
          inputProps={{ maxLength: 255 }}
          onChange={handleChange}
        />


        {/* divider */}
        {/* activity count & duration + save button on the left */}

        <Grid
          container
          justify="flex-start"
          alignItems="flex-end"
          spacing={2}
        >
          {type === "activity" ?
            <>
              <Grid item>
                <FormControl className={clsx(classes.margin, classes.textFieldAdornment)}>
                  <InputLabel htmlFor="practice-duration-label" shrink>Practice Duration</InputLabel>
                  <Input
                    id="practice-duration"
                    name="practice_duration"
                    value={activity_add_form.practice_duration !== null ? activity_add_form.practice_duration : 0}
                    onChange={handleChange}
                    margin="dense"
                    type="number"
                    endAdornment={<InputAdornment position="end">mins</InputAdornment>}
                  />
                  <FormHelperText id="practice-duration-helper-text">How long did you practice?</FormHelperText>
                </FormControl>
              </Grid>
              <Grid item>
                <TextField
                  id="activity-count"
                  name="activity_count"
                  // style={{ margin: 8 }}
                  label="Activity Count"
                  className={classes.margin}
                  helperText="How many practice did you acomplished?"
                  InputLabelProps={{
                    shrink: true,
                  }}
                  value={activity_add_form.activity_count}
                  margin="dense"
                  type="number"
                  onChange={handleChange}
                />
              </Grid>
              <Grid item xs>
                <Grid
                  container
                  justify="flex-end"
                  alignItems="flex-end"
                  spacing={2}
                >
                  <Grid item>
                    <CardActions>
                      <Button variant="contained" color="primary" onClick={handleSave}>Save</Button>
                    </CardActions>
                  </Grid>
                </Grid>
              </Grid>
            </>
            : type === "result" ?
              <>
                <Grid item>
                  <FormControl className={clsx(classes.margin, classes.textFieldAdornment)}>
                    <InputLabel htmlFor="test-duration-label" shrink>Test Duration</InputLabel>
                    <Input
                      id="test-duration"
                      name="test_duration"
                      value={result_add_form.test_duration !== null ? result_add_form.test_duration : 0}
                      onChange={handleChange}
                      margin="dense"
                      type="number"
                      endAdornment={<InputAdornment position="end">mins</InputAdornment>}
                    />
                    <FormHelperText id="test-duration-helper-text">How long did you test?</FormHelperText>
                  </FormControl>
                </Grid>
                <Grid item>
                  <TextField
                    id="test-count"
                    name="test_count"
                    // style={{ margin: 8 }}
                    label="Test Count"
                    className={classes.margin}
                    helperText="How many test did you acomplished?"
                    InputLabelProps={{
                      shrink: true,
                    }}
                    value={result_add_form.test_count}
                    margin="dense"
                    type="number"
                    onChange={handleChange}
                  />
                </Grid>
                <Grid item xs>
                  <Grid
                    container
                    justify="flex-end"
                    alignItems="flex-end"
                    spacing={2}
                  >
                    <Grid item>
                      <CardActions>
                        <Button variant="contained" color="primary" onClick={handleSave}>Save</Button>
                      </CardActions>
                    </Grid>
                  </Grid>
                </Grid>
              </>
              : null}


        </Grid>

      </CardContent>
    </Card>
  );
};

export default AddForm;