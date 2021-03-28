import { changeField, changeFieldWoForm } from '../../modules/activity';
import axios from './client';
import Alert from '../alert';

export const ActivityList = (dispatch) => {
  const get_data = {
    offset: 0,
    limit: 1000000,
  }
  axios.get('/posting/activity', { params: get_data })
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        dispatch(changeFieldWoForm({
          key: 'activityList',
          value: res.data.data
        }))
      } else {
        dispatch(changeFieldWoForm({
          key: 'activityList',
          value: {}
        }))
      }
    }
  })
}

export const RecentActivityList = (dispatch) => {
  const get_data = {
    offset: 0,
    limit: 1,
  }
  axios.get('/posting/activity', { params: get_data })
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        dispatch(changeFieldWoForm({
          key: 'recentActivityList',
          value: res.data.data
        }))
      } else {
        dispatch(changeFieldWoForm({
          key: 'recentActivityList',
          value: {}
        }))
      }
    }
  })
}

export const BestActivityList = (dispatch, type) => { // type is ether "hour" or "count"
  const get_data = {
    offset: 0,
    limit: 1,
  }
  axios.get(`/posting/activity/${type}`, { params: get_data })
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        if (type === "hour") {
          dispatch(changeFieldWoForm({
            key: 'bestActivityDuration',
            value: res.data.data
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'bestActivityCount',
            value: res.data.data
          }))
        }

      } else {
        if (type === "hour") {
          dispatch(changeFieldWoForm({
            key: 'bestActivityDuration',
            value: {}
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'bestActivityCount',
            value: {}
          }))
        }
      }
    }
  })
}

export const WeeklyActivityList = (dispatch, type, categorySelected) => { // type is ether "hour" or "count"
  const get_data = {
    category_name: categorySelected
  }
  axios.get(`/posting/weekly/activity/${type}`, { params: get_data })
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        if (type === "hour") {
          dispatch(changeFieldWoForm({
            key: 'weeklyActivityHourSummary',
            value: res.data.data
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'weeklyActivityCountSummary',
            value: res.data.data
          }))
        }

      } else {
        if (type === "hour") {
          dispatch(changeFieldWoForm({
            key: 'weeklyActivityHourSummary',
            value: {}
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'weeklyActivityCountSummary',
            value: {}
          }))
        }
      }
    }
  })
}


export const DeleteActivity = (dispatch, id) => {
  axios.post(`/posting/delete/${id}`)
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      Alert(0, "Deleting activity is successfully completed!", 'Okay', null, null);
      ActivityList(dispatch);
    }
  })
}
