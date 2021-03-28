import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';

const CHANGE_FIELD = 'addForm/CHANGE_FIELD';
const INITIALIZE_FORM = 'addForm/INITIALIZE_FORM';



export const changeField = createAction(
  CHANGE_FIELD,
  ({ form, key, value }) => ({
    form, // add_node, add_instance, add_task
    key, // ip, port, ...
    value // 실제 바꾸려는 값
  })
);
export const initializeForm = createAction(INITIALIZE_FORM, form => form); // add_node, add_instance

const initialState = {
  activity_add_form: {
    // category_name: "", //AutoComplete 
    content: "",
    practice_duration: 0,
    activity_count: 0,
  },
  result_add_form: {
    // category_name: "",
    content: "",
    test_duration: 0,
    test_count: 0,
  }
};

const addForm = handleActions(
  {
    [CHANGE_FIELD]: (state, { payload: { form, key, value } }) =>
      produce(state, draft => {
        draft[form][key] = value; // 예: state.register.username을 바꾼다
      }),
    [INITIALIZE_FORM]: (state, { payload: form }) => ({
      ...state,
      [form]: initialState[form],
    }),
  },
  initialState
);

export default addForm;