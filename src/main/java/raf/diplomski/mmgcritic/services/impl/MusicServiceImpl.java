package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.repositories.MusicRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl {
    private final MusicRepository musicRepository;
    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElseThrow();
    }

    public Music getMusicByName(String name) {
        return musicRepository.findMusicByTitle(name).orElseThrow();
    }

    public Music addNewMusic(Music  music) {
        return musicRepository.save(music);
    }

    public Music updateMusic(Music music) {
        //todo cba rn
        return music;
    }

    public Boolean deleteMusic(Long id){
        try {
        musicRepository.deleteById(id);
        return true;
        }catch (Exception e){
            return false;
        }

    }
    public List<Music> findAlbumsByTitleContaining(String string){
        return musicRepository.findAllByTitleContaining(string);
    }
    public List<Music> findAlbumsByReleaseDate(){
        return musicRepository.findNewestAlbums();
    }
    public List<Music> findAlbumsByArtistName(String artistName){
        return musicRepository.findAlbumsByArtistName(artistName);
    }
    public List<Music> findTopRatedAlbums(){
        return musicRepository.findTopRatedAlbums();
    }


}
