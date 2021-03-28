import { changeField, changeFieldWoForm } from '../../modules/category';
import axios from './client';
import Alert from '../alert';

export const CategoryList = (dispatch) => {
  // const data = {
  // }
  axios.get('/category')
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      document.getElementById("alert-buton-0").focus();
    } else {
      if(res.data.data.total !== undefined && res.data.data.total !== null && res.data.data.total > 0) {
        dispatch(changeFieldWoForm({
          key: 'categoryList',
          value: res.data.data
        }))
      } else {
        dispatch(changeFieldWoForm({
          key: 'categoryList',
          value: {}
        }))
      }
    }
  })
}

export const CreateCategory = (dispatch, value, handleClose) => {
  const data = {
    category_name: value
  }
  axios.post('/category', data)
  .then((res) => {
    if (res.data === undefined) { // client return 400 level error
      return ;
    }
    if (res.data.success !== true) {
      Alert(0, res.data.msg, 'Okay', null, null);
      
    } else {
      Alert(0, "New category is successfully completed!", 'Okay', null, null);
      CategoryList(dispatch);
        handleClose();
    }
  })
}