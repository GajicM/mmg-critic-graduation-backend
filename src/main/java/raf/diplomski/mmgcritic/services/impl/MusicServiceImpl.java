package raf.diplomski.mmgcritic.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raf.diplomski.mmgcritic.data.dto.MusicDto;
import raf.diplomski.mmgcritic.data.entities.music.Music;
import raf.diplomski.mmgcritic.data.mapper.MusicMapper;
import raf.diplomski.mmgcritic.repositories.MusicRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl {
    private final MusicRepository musicRepository;
    private final MusicMapper mapper;
    public List<MusicDto> getAllMusic() {
        return musicRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public MusicDto getMusicById(Long id) {
        return mapper.toDto(musicRepository.findById(id).orElseThrow());
    }

    public MusicDto getMusicByName(String name) {
        return mapper.toDto(musicRepository.findMusicByTitle(name).orElseThrow()) ;
    }

    public MusicDto addNewMusic(MusicDto  music) {
        return mapper.toDto(musicRepository.save(mapper.fromDto(music)));
    }

    public MusicDto updateMusic(MusicDto music) {
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
    public List<MusicDto> findAlbumsByTitleContaining(String string){
        return musicRepository.findAllByTitleContaining(string).stream().map(mapper::toDto).toList();
    }
    public List<MusicDto> findAlbumsByReleaseDate(){
        return musicRepository.findNewestAlbums().stream().map(mapper::toDto).toList();
    }
    public List<MusicDto> findAlbumsByArtistName(String artistName){
        return musicRepository.findAlbumsByArtistName(artistName).stream().map(mapper::toDto).toList();
    }
    public List<MusicDto> findTopRatedAlbums(){
        return musicRepository.findTopRatedAlbums().stream().map(mapper::toDto).toList();
    }


}
