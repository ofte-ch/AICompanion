package ch.ofte.mplayer.server.music;

import java.math.BigInteger;

public interface MusicService {
    int addNewMusic();
    Music getMusicById(long id);
    void deleteMusicById(long id);
}
