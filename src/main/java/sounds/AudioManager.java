package sounds;

public class AudioManager {

    private double musicVolume;
    private double effectsVolume;
    private GameMusic[] musicTracks;
    private SoundEffect[] soundEffects;

    public AudioManager(){
        musicVolume = 1.0;
        effectsVolume = 1.0;
        loadMusic();
        loadEffects();
    }

    private void loadMusic(){
        musicTracks = new GameMusic[2];
        musicTracks[0] = new GameMusic("/music/BlueBoyAdventure.mp3");
        musicTracks[0].setVolume(musicVolume);
        musicTracks[1] = new GameMusic("/music/finalBoss.mp3");
        musicTracks[1].setVolume(musicVolume);

    }
    private void loadEffects() {
        soundEffects = new SoundEffect[9];
        soundEffects[0] = new SoundEffect("/music/weaponSwipe.mp3");
        soundEffects[0].setVolume(effectsVolume);
        soundEffects[1] = new SoundEffect("/music/healthRestore.wav");
        soundEffects[1].setVolume(effectsVolume);
        soundEffects[2] = new SoundEffect("/music/coinSound.mp3");
        soundEffects[2].setVolume(effectsVolume);
        soundEffects[3] = new SoundEffect("/music/Hit.wav");
        soundEffects[3].setVolume(effectsVolume);
        soundEffects[4] = new SoundEffect("/music/Damaged.wav");
        soundEffects[4].setVolume(effectsVolume);
        soundEffects[5] = new SoundEffect("/music/knife_swish.mp3");
        soundEffects[5].setVolume(effectsVolume);
        soundEffects[6] = new SoundEffect("/music/door-open.mp3");
        soundEffects[6].setVolume(effectsVolume);
        soundEffects[7] = new SoundEffect("/music/GameOver.wav");
        soundEffects[7].setVolume(effectsVolume);
        soundEffects[8] = new SoundEffect("/music/winning.mp3");
        soundEffects[8].setVolume(effectsVolume);
    }

    public void setMusicVolume(double volume) {
        this.musicVolume = volume;
        for (GameMusic musicTrack : musicTracks) {
            musicTrack.setVolume(volume);
        }
    }
    public GameMusic getMusicTrack(int i) {
        return musicTracks[i];
    }
    public void setEffectsVolume(double volume) {
        this.effectsVolume = volume;
        for (SoundEffect soundEffect : soundEffects) {
            soundEffect.setVolume(volume);
        }
    }
    public SoundEffect getSoundEffect(int i) {
        return soundEffects[i];
    }
    public double getMusicVolume() {
        return musicVolume;
    }
    public double getEffectsVolume() {
        return effectsVolume;
    }

}
