import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';
// import { takeLatest } from 'redux-saga/effects';
// import createRequestSaga, {
//   createRequestActionTypes
// } from '../lib/createRequestSaga';
// import * as categoryAPI from '../lib/api/category';

const CHANGE_FIELD = 'category/CHANGE_FIELD';
const CHANGE_FIELD_WO_FORM = 'category/CHANGE_FIELD_WO_FORM';
const INITIALIZE_FORM = 'category/INITIALIZE_FORM';
const INITIALIZE_STATE = 'cateogry/INITIALIZE_STATE';

export const changeField = createAction(
  CHANGE_FIELD,
  ({ form, key, value }) => ({
    form,
    key,
    value
  })
);
export const changeFieldWoForm = createAction(
  CHANGE_FIELD_WO_FORM,
  ({ key, value }) => ({
    key,
    value
  })
);
export const initializeForm = createAction(INITIALIZE_FORM, form => form);
export const initializeState = createAction(INITIALIZE_STATE);

const initialState = {
  categoryList: {},
};

const category = handleActions(
  {
    [CHANGE_FIELD]: (state, { payload: { form, key, value } }) =>
    produce(state, draft => {
      draft[form][key] = value;
    }),
    [CHANGE_FIELD_WO_FORM]: (state, { payload: { key, value } }) =>
    produce(state, draft => {
      draft[key] = value;
    }),
    [INITIALIZE_FORM]: (state, { payload: form }) => ({
      ...state,
      [form]: initialState[form],
    }),
    [INITIALIZE_STATE]: () => ({
      ...initialState
    }),
  },
  initialState
)

export default category;