import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';

import auth, { authSaga } from './auth';
import addForm from './addForm';
import activity from './activity';
import result from './result';
import category from './category';
import loading from './loading';

const rootReducers = combineReducers({
    auth,
    addForm,
    activity,
    result,
    category,
    loading,
})

export function* rootSaga() {
    yield all([authSaga()]);
}

export default rootReducers;