const  { createUser,  findUserByEmail,  verifyUser, 
     getAllUsers,  updateUserNickname,   deleteUser,  findUserById
} = require('./models/users');

async function runTests(){
   try{
    //1. 회원가입
    await  createUser( 't@t',  't',  'tt',  '0101111111',  1,  '1.png');  
    console.log('✅ createUser 성공');

    //2. 이메일로 조회
    const userByEmail =  await  findUserByEmail( 't@t'); 


    
    console.log('✅ findUserByEmail 결과: ', userByEmail);

    //3. 로그인검증 (비밀번호 맞는 경우)
    const loginUser =  await  verifyUser( 't@t' , 't');  
    console.log('✅ verifyUser 결과(비밀번호 맞는 경우): ', loginUser);

    //4. 로그인검증 (비밀번호 틀린 경우)
    const failLogin =  await  verifyUser( 't@t' , 'wrong');  
    console.log('✅ verifyUser 결과(비밀번호 틀린 경우): ', failLogin);
    
    //5. 전체조회   getAllUsers
    const allUsers = await  getAllUsers();  
    console.log('✅ getAllUsers 결과:' ,allUsers );
    
    //6. 닉네임수정 updateUserNickname 
    await  updateUserNickname(userByEmail.APP_USER_ID  ,'AA');  
    const updateUser = await findUserById(userByEmail.APP_USER_ID);
    console.log('✅ updateUserNickname 결과:' ,updateUser );

    //7. 삭제      deleteUser 
    await  deleteUser(userByEmail.APP_USER_ID );  
    console.log('✅ deleteUser 성공');
    const deletedUser = await findUserById(userByEmail.APP_USER_ID);
    console.log('✅ findUserById (삭제 후)' , deletedUser);

   }catch(err){  console.error('❌ 테스트 중 오류 발생:' , err); }
}
runTests();

//  node   test1.js
