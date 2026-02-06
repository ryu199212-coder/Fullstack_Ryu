// pages/users.js
import { useDispatch, useSelector } from 'react-redux'; // 액션, 스토어의 상태
import {
  LOAD_USERS_REQUEST,
  LOG_OUT_REQUEST,
  UPDATE_NICKNAME_REQUEST,
  DELETE_USER_REQUEST,
} from '../reducers/user';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';

// 1. useSelector → Redux store에서 사용자 상태 가져오기
// 2. useEffect   → 로그인 여부 확인 및 사용자 목록 불러오기
// 3. dispatch    → 액션 발생 (로그인, 사용자 삭제 등)

export default function UsersPage() {
  const dispatch = useDispatch();
  const router = useRouter();

  // 리듀서에서 필요한 상태 가져오기
  const { users, isLoading, error, me } = useSelector((state) => state.user);

  // 닉네임 수정 상태 관리
  const [editId, setEditId] = useState(null);
  const [newNickname, setNewNickname] = useState('');

  // 로그인 여부 체크 및 사용자 목록 불러오기
  useEffect(() => {
    if (!me) {
      router.push('/login'); // 로그인 안되어 있으면 로그인 페이지로 이동
    } else {
      dispatch({ type: LOAD_USERS_REQUEST }); // 사용자 목록 요청
    }
  }, [dispatch, me, router]);

  // 로그아웃 후 me가 null이 되면 로그인 페이지로 이동
  useEffect(() => {
    if (me == null) {
      router.push('/login');
    }
  }, [me, router]);

  // 로그아웃
  const onLogout = () => {
    dispatch({ type: LOG_OUT_REQUEST });
  };

  // 사용자 삭제
  const onDelete = (id) => {
    dispatch({type: DELETE_USER_REQUEST, data : {id, nickname:newNickname}});
  };

  // 닉네임 수정모드로 전환   const [editId, setEditId] = useState(null);
  const onEdit = (id) => setEditId(id);

  // 닉네임 수정 완료
  const onUpdateNickname = (id) => {
    dispatch({type:UPDATE_NICKNAME_REQUEST, data: {id,nickname:newNickname} });
    setEditId(null);
    setNewNickname('');
  };

  // 렌더링
  return (
    <div className="container mt-4">
      <h1 className="mb-3">사용자 목록</h1>

      {/* 로딩/에러 상태 표시 */}
      {isLoading && <div className="alert alert-info">로딩 중...</div>}
      {error && <div className="alert alert-danger">에러 메시지</div>}

      {/* 사용자 목록 테이블 */}
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

      {/* 로그아웃 버튼 */}
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