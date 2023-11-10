package nl.fontysS3_project.persistence.impl;

/*
@Repository
public class FakeUserRepositoryImpl{
    private static int NEXT_ID = 1;
    private final List<UserEntity> savedUsers;

    public FakeUserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }

    @Override
    public void deleteById(int userId) {
        this.savedUsers.removeIf(userEntity -> userEntity.getId() == userId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.savedUsers
                .stream()
                .anyMatch(userEntity -> userEntity.getUsername().equals(username));
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getId() < 1) {
            user.setId(NEXT_ID);
            NEXT_ID++;

        }
        this.savedUsers.add(user);
        return user;
    }

    @Override
    public Optional<UserEntity> findById(int userId) {
        return this.savedUsers.stream()
                .filter(userEntity -> userEntity.getId() == userId)
                .findFirst();
    }
    @Override
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(this.savedUsers);
    }

    @Override
    public int count() {
        return this.savedUsers.size();
    }

    @Override
    public int checkLoginPassword(String username, String password){
        UserEntity foundUser = null;
        for (UserEntity user : savedUsers) {
            if (user.getUsername().equals(username)) {
                foundUser = user;
                break;
            }
        }
        assert foundUser != null;
        if (foundUser.getHashedPassword().equals(password)){
            return foundUser.getId();
        }
        else{
            return -1;
        }
    }
}*/
