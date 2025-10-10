package ch.ofte.aicompanion.server.music;

public interface MusicService {
    int addNewMusic();
    Music getMusicById(long id);
    void deleteMusicById(long id);
}
