package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.InternalCharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InternalCharacterServiceImpl implements InternalCharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper charactersMapper;

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        List<Character> characters = characterRepository.findByNameContaining(name);
        return charactersMapper.listToDto(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomLong = new Random().nextLong(characterRepository.count());
        Character characters = characterRepository.findById(randomLong)
                .orElseThrow(() -> new RuntimeException(
                        "An error while pulling a random character"));
        CharacterDto charactersDto = charactersMapper.toDto(characters);
        return charactersDto;
    }
}
