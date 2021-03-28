import { initializeForm } from '../../modules/addForm';
import { ActivityList } from './activity';
import { ResultList } from './result';

import axios from "./client";
import Alert from '../alert';

export const CreateActivity = (dispatch, activity_add_form, categorySelected) => {
  const data = {
    category_name: categorySelected.category_name,
    content: activity_add_form.content,
    activity_count: activity_add_form.activity_count,
    practice_duration: activity_add_form.practice_duration,
  }
  axios.post('/posting/activity/create', data)
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      Alert(0, "New Activity is successfully completed!", 'Okay', null, null);
      ActivityList(dispatch);
      dispatch(initializeForm('activity_add_form'));
    }
  })
}

export const CreateResult = (dispatch, result_add_form, categorySelected) => {
  const data = {
    category_name: categorySelected.category_name,
    content: result_add_form.content,
    test_count: result_add_form.test_count,
    test_duration: result_add_form.test_duration,
  }
  axios.post('/posting/result/create', data)
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      Alert(0, "New result is successfully completed!", 'Okay', null, null);
      ResultList(dispatch);
      dispatch(initializeForm('result_add_form'));
    }
  })
}