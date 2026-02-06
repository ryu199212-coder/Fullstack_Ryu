export const LOG_IN_REQUEST = 'LOG_IN_REQUEST';
export const LOG_IN_SUCCESS = 'LOG_IN_SUCCESS';
export const LOG_IN_FAILURE = 'LOG_IN_FAILURE';

export const LOG_OUT_REQUEST = 'LOG_OUT_REQUEST';
export const LOG_OUT_SUCCESS = 'LOG_OUT_SUCCESS';
export const LOG_OUT_FAILURE = 'LOG_OUT_FAILURE';

export const SIGN_UP_REQUEST = 'SIGN_UP_REQUEST';
export const SIGN_UP_SUCCESS = 'SIGN_UP_SUCCESS';
export const SIGN_UP_FAILURE = 'SIGN_UP_FAILURE';

export const LOAD_USERS_REQUEST = 'LOAD_USERS_REQUEST';
export const LOAD_USERS_SUCCESS = 'LOAD_USERS_SUCCESS';
export const LOAD_USERS_FAILURE = 'LOAD_USERS_FAILURE';

export const UPDATE_NICKNAME_REQUEST = 'UPDATE_NICKNAME_REQUEST';
export const UPDATE_NICKNAME_SUCCESS = 'UPDATE_NICKNAME_SUCCESS';
export const UPDATE_NICKNAME_FAILURE = 'UPDATE_NICKNAME_FAILURE';

export const DELETE_USER_REQUEST = 'DELETE_USER_REQUEST';
export const DELETE_USER_SUCCESS = 'DELETE_USER_SUCCESS';
export const DELETE_USER_FAILURE = 'DELETE_USER_FAILURE';

export const initialState = {
  me: null,
  users: [],
  isLoading: false,
  error: null,
  signUpDone: false,
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case LOG_IN_REQUEST:
    case LOG_OUT_REQUEST:
    case SIGN_UP_REQUEST:
    case LOAD_USERS_REQUEST:
    case UPDATE_NICKNAME_REQUEST:
    case DELETE_USER_REQUEST:
      return { ...state, isLoading: true, error: null };

    case LOG_IN_SUCCESS:
      return { ...state, isLoading: false, me: action.data };

    case LOG_OUT_SUCCESS:
      return { ...state, isLoading: false, me: null };

    case SIGN_UP_SUCCESS:
      return { ...state, isLoading: false, signUpDone: true };

    case LOAD_USERS_SUCCESS:
      return { ...state, isLoading: false, users: action.data };

    case UPDATE_NICKNAME_SUCCESS:
      return {
        ...state,
        isLoading: false,
        me:
          state.me && state.me.id === action.data.id
            ? { ...state.me, nickname: action.data.nickname }
            : state.me,
        users: state.users.map((u) =>
          u.id === action.data.id ? { ...u, nickname: action.data.nickname } : u
        ),
      };

    case DELETE_USER_SUCCESS:
      return {
        ...state,
        isLoading: false,
        users: state.users.filter((u) => u.id !== action.data.id),
        me: state.me?.id === action.data.id ? null : state.me,
      };

    case LOG_IN_FAILURE:
    case LOG_OUT_FAILURE:
    case SIGN_UP_FAILURE:
    case LOAD_USERS_FAILURE:
    case UPDATE_NICKNAME_FAILURE:
    case DELETE_USER_FAILURE:
      return {
        ...state,
        isLoading: false,
        error: action.error?.message || action.error,
      };

    default:
      return state;
  }
};

export default reducer;