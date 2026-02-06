import { useDispatch, useSelector } from 'react-redux'; 
import {
  LOAD_USERS_REQUEST,
  LOG_OUT_REQUEST,
  UPDATE_NICKNAME_REQUEST,
  DELETE_USER_REQUEST,
} from '../reducers/user';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';


export default function UsersPage() {
  const dispatch = useDispatch();
  const router = useRouter();

  const { users, isLoading, error, me } = useSelector((state) => state.user);

  const [editId, setEditId] = useState(null);
  const [newNickname, setNewNickname] = useState('');


  useEffect(() => {
    if (!me) {
      router.push('/login'); 
    } else {
      dispatch({ type: LOAD_USERS_REQUEST }); 
    }
  }, [dispatch, me, router]);

  useEffect(() => {
    if (me == null) {
      router.push('/login');
    }
  }, [me, router]);

  const onLogout = () => {
    dispatch({ type: LOG_OUT_REQUEST });
  };

  const onDelete = (id) => {
    dispatch({type: DELETE_USER_REQUEST, data : {id, nickname:newNickname}});
  };

  const onEdit = (id) => setEditId(id);

  const onUpdateNickname = (id) => {
    dispatch({type:UPDATE_NICKNAME_REQUEST, data: {id,nickname:newNickname} });
    setEditId(null);
    setNewNickname('');
  };

  // 렌더링
  return (
    <div className="container mt-4">
      <h1 className="mb-3">사용자 목록</h1>

      {isLoading && <div className="alert alert-info">로딩 중...</div>}
      {error && <div className="alert alert-danger">에러 메시지</div>}

      <table className="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th>이메일</th>
            <th>닉네임</th>
            <th>액션</th>
          </tr>
        </thead>
        <tbody>
        {users.map((u)=> (
          <tr key={u.id}>
            <td>{u.email}</td>
            <td>{  editId === u.id
                ? (<input className='form-control'
                    value={newNickname}
                    onChange={(e)=>setNewNickname(e.target.value)}
                    placeholder="새 닉네임"  />)
                : (u.nickname)
                }
            </td>
            <td>{editId === u.id
             ? (<button className="btn btn-primary btn-sm me-2" onClick={()=> onUpdateNickname(u.id)} >수정 완료</button>)
             : (<button className="btn btn-primary btn-sm me-2" onClick={()=> onEdit(u.id)}>닉네임 수정</button>)
            }
              <button className="btn btn-danger btn-sm" onClick={()=> onDelete(u.id)}>삭제</button>
            </td>
          </tr>
        )) }
        </tbody>
      </table>

      {me && (
        <div className="mt-3">
          <button className="btn btn-secondary" onClick={onLogout}>
            로그아웃
          </button>
        </div>
      )}
    </div>
  );
}