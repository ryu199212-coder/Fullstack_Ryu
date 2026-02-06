# ■ file
# ■ network

2. -------------------
# ■ Github - revert / reset
1. revert - 안전한 취소(공용)
2. reset  - 아예 지워버리기(혼자)


실습(1) revert
1. 본인 브랜치에서 파일작성 - teset브랜치에서 새 작업 하고 원격 저장소에 푸쉬
```bash
git branch
git checkout 테스트 브랜치명
파일작성 revert.md
git add .
git commit -m "revert"
git push origin 테스트 브랜치명
```

2. main 브랜치에서 병합 - test브랜치 작업을 main 병합 하고 푸시
```bash
git checkout master
git pull origin master
git merge 테스트 브랜치명
git push origin master
```

3. main 브랜치에서 되돌리기
테스트 브랜치명
```bash
git checkout master
git log
git revert <commit_hash>
git push origin master
```

Q.1 팀장 - test3.md revert vs reset
    main - commit1 - merge
    main - commit2 - merge
    main - commit3 - merge

Q.2 팀원 - 예) hj.md 본인소개 / commit / main에서 merge - revert 해서 본인 흔적 지우기
3. -------------------
# ■ Oracle - select
# ■ Group





4. -------------------
-- 자격증 및 정리!