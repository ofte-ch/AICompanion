package ch.ofte.mplayer.server.music;

import org.springframework.stereotype.Service;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Override
    public int addNewMusic() {
        return 0;
    }

    @Override
    public Music getMusicById(long id) {
        Music m = new Music();
        m.setId(id);
        return m;
    }

    @Override
    public void deleteMusicById(long id) {

    }
}
