public class ActorCollection {
    private final List<ActiveActorDestructible> friendlyUnits = new ArrayList<>();
    private final List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
    private final List<ActiveActorDestructible> userProjectiles = new ArrayList<>();
    private final List<ActiveActorDestructible> enemyProjectiles = new ArrayList<>();

    public List<ActiveActorDestructible> getFriendlyUnits() {
        return Collections.unmodifiableList(friendlyUnits);
    }

    public List<ActiveActorDestructible> getEnemyUnits() {
        return Collections.unmodifiableList(enemyUnits);
    }

    public List<ActiveActorDestructible> getUserProjectiles() {
        return Collections.unmodifiableList(userProjectiles);
    }

    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return Collections.unmodifiableList(enemyProjectiles);
    }

    public Stream<ActiveActorDestructible> getAllActors() {
        return Stream.of(friendlyUnits, enemyUnits, userProjectiles, enemyProjectiles)
            .flatMap(List::stream);
    }

    public void removeActor(ActiveActorDestructible actor) {
        friendlyUnits.remove(actor);
        enemyUnits.remove(actor);
        userProjectiles.remove(actor);
        enemyProjectiles.remove(actor);
    }

    // Add methods for each actor type
    public void addFriendlyUnit(ActiveActorDestructible unit) {
        friendlyUnits.add(unit);
    }

    // ... other add methods ...
} 