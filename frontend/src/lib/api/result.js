import { changeFieldWoForm } from '../../modules/result';
import axios from './client';
import Alert from '../alert';

export const ResultList = (dispatch) => {
  const get_data = {
    offset: 0,
    limit: 1000000,
  }
  axios.get('/posting/result', { params: get_data })
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        dispatch(changeFieldWoForm({
          key: 'resultList',
          value: res.data.data
        }))
      } else {
        dispatch(changeFieldWoForm({
          key: 'resultList',
          value: {}
        }))
      }
    }
  })
}

export const RecentResultList = (dispatch) => {
  const get_data = {
    offset: 0,
    limit: 1,
  }
  axios.get('/posting/result', { params: get_data })
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        dispatch(changeFieldWoForm({
          key: 'recentResultList',
          value: res.data.data
        }))
      } else {
        dispatch(changeFieldWoForm({
          key: 'recentResultList',
          value: {}
        }))
      }
    }
  })
}

export const BestResultList = (dispatch, type) => { // type is ether "hour" or "count"
  const get_data = {
    offset: 0,
    limit: 1,
  }
  axios.get(`/posting/result/${type}`, { params: get_data })
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
            key: 'bestResultDuration',
            value: res.data.data
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'bestResultCount',
            value: res.data.data
          }))
        }

      } else {
        if (type === "hour") {
          dispatch(changeFieldWoForm({
            key: 'bestResultDuration',
            value: {}
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'bestResultCount',
            value: {}
          }))
        }
      }
    }
  })
}

export const WeeklyResultList = (dispatch, type, categorySelected) => { // type is ether "hour" or "count"
  const get_data = {
    category_name: categorySelected
  }
  axios.get(`/posting/weekly/result/${type}`, { params: get_data })
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
            key: 'weeklyResultHourSummary',
            value: res.data.data
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'weeklyResultCountSummary',
            value: res.data.data
          }))
        }

      } else {
        if (type === "hour") {
          dispatch(changeFieldWoForm({
            key: 'weeklyResultHourSummary',
            value: {}
          }))
        } else if (type === "count") {
          dispatch(changeFieldWoForm({
            key: 'weeklyResultCountSummary',
            value: {}
          }))
        }
      }
    }
  })
}



export const DeleteResult = (dispatch, id) => {
  axios.post(`/posting/delete/${id}`)
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      Alert(0, "Deleting result is successfully completed!", 'Okay', null, null);
      ResultList(dispatch);
    }
  })
}