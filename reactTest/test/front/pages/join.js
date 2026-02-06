import { useDispatch  , useSelector } from 'react-redux';  
import { useState     , useEffect }   from 'react'; 
import { useRouter }  from 'next/router';
import { SIGN_UP_REQUEST } from '../reducers/user'; 

export default function JoinPage(){
    // 코드 
    const  dispatch = useDispatch();
    const  router   = useRouter();
    const { me ,isLoading, error , signUpDone }  = useSelector( (state)=> state.user );  

    const [email, setEmail]       = useState('');  
    const [password, setPassword] = useState(''); 
    const [nickname, setNickname] = useState(''); 

    const onSubmit = (e)=>{
        e.preventDefault();
        if(!email.trim()){
            console.log('이메일이 빈칸입니다.');
            alert('이메일을 입력해주세요');
            return;
        }
        
        if(!password.trim()){
            console.log('비밀번호가 빈칸입니다.');
            alert('비밀번호를 입력해주세요');
            return;
        }
        
        if(!nickname.trim()){
            console.log('닉네임이 빈칸입니다.');
            alert('닉네임을 입력해주세요');
            return;
        }

        dispatch({ type : SIGN_UP_REQUEST , data : { email, password, nickname}});
    };

    useEffect( ()=>{ if(me) router.push('/users');   } , [me, router]);    

    useEffect( ()=>{ if(signUpDone) router.push('/login');   } , [signUpDone, router]);   

    // 렌더링
    return (
    <div className="container mt-4">
        <h2 className="mb-3">회원가입</h2> 
        <form  onSubmit={onSubmit}  className="w-50 mx-auto">
        <div class="mb-3">
            <input type="email" className="form-control" placeholder="이메일"  value={email}
            onChange={ (e)=> setEmail(e.target.value) }     />
        </div>

        <div className="mb-3">
            <input type="password" className="form-control" placeholder="비밀번호"  value={password}
            onChange={ (e)=> setPassword(e.target.value) }   />
        </div>

        <div className="mb-3">
            <input type="text" className="form-control" placeholder="닉네임" value={nickname}
            onChange={ (e)=> setNickname(e.target.value) }  />
        </div>

        <button type="submit" className="btn btn-primary w-100"    disabled={isLoading}>   회원가입 </button>
        </form> 
        {error && <div className="alert alert-danger mt-3"> {error} </div> }
  </div>
    );
}
