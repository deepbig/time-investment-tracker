import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';

const CHANGE_FIELD = 'result/CHANGE_FIELD';
const CHANGE_FIELD_WO_FORM = 'result/CHANGE_FIELD_WO_FORM';
const INITIALIZE_FORM = 'result/INITIALIZE_FORM';
const INITIALIZE_STATE = 'result/INITIALIZE_STATE';

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
  resultList: {},
  recentResultList: {},
  bestResultDuration: {},
  bestResultCount: {},
  weeklyResultCountSummary: {},
  weeklyResultHourSummary: {},
}

const result = handleActions(
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

export default result;