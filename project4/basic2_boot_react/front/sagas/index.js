// sagas/index.js 
import { all,fork } from 'redux-saga/effects';

import authSaga      from './authSaga';
import postSaga      from './postSaga';
import commentSaga   from './commentSaga';
import followSaga    from './followSaga';
import likeSaga      from './likeSaga';
import retweetSaga   from './retweetSaga';
import materialSaga from './materialSaga';

export default  function * rootSaga(){
  yield all([
    fork( authSaga ) ,
    fork( postSaga ) ,
    fork( commentSaga ) ,
    fork( followSaga ) ,
    fork( likeSaga ) ,
    fork( retweetSaga ) ,
    fork(materialSaga),
  ]);  
} 
