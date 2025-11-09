package cv5;

import java.util.*;
import java.util.stream.Collectors;

public class MethodRoche {

    // Частотные словари
    private static final String ENGLISH_FREQUENCY = "etaoinshrdlcumwfgypbvkjxqz";
    private static final String SLOVAK_FREQUENCY = "aoenirstlvkudmpchzbjgyfxqw";

    /**
     * Главный метод для взлома шифра замены (декрипт)
     */
    public static String decrypt(String ciphertext, String language) {
        if (ciphertext == null || ciphertext.isEmpty()) {
            return "";
        }

        String frequencyOrder = getFrequencyOrder(language);
        Map<Character, Integer> cipherFrequencies = calculateFrequencies(ciphertext);
        List<Character> cipherCharsByFrequency = sortByFrequency(cipherFrequencies);
        Map<Character, Character> mapping = createMapping(cipherCharsByFrequency, frequencyOrder);

        return applyMapping(ciphertext, mapping);
    }

    /**
     * Главный метод для шифрования (простая замена)
     * В реальном шифре замены используется ключ-перестановка алфавита
     */
    public static String encrypt(String plaintext, Map<Character, Character> substitutionMap) {
        if (plaintext == null || plaintext.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerChar = Character.toLowerCase(c);
                char encryptedChar = substitutionMap.getOrDefault(lowerChar, c);

                if (Character.isUpperCase(c)) {
                    result.append(Character.toUpperCase(encryptedChar));
                } else {
                    result.append(encryptedChar);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    // ==================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ====================

    /**
     * Генерирует случайный ключ для шифра замены
     */
    public static Map<Character, Character> generateRandomKey() {
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }

        Collections.shuffle(alphabet);

        Map<Character, Character> key = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            key.put((char) ('a' + i), alphabet.get(i));
        }

        return key;
    }

    /**
     * Создает ключ на основе пароля (более безопасный способ)
     */
    public static Map<Character, Character> generateKeyFromPassword(String password) {
        // Убираем дубликаты из пароля и добавляем остальной алфавит
        Set<Character> usedChars = new LinkedHashSet<>();

        // Добавляем уникальные символы из пароля
        for (char c : password.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                usedChars.add(c);
            }
        }

        // Добавляем остальные буквы алфавита
        for (char c = 'a'; c <= 'z'; c++) {
            usedChars.add(c);
        }

        // Создаем маппинг
        Map<Character, Character> key = new HashMap<>();
        List<Character> substitution = new ArrayList<>(usedChars);

        for (int i = 0; i < 26; i++) {
            key.put((char) ('a' + i), substitution.get(i));
        }

        return key;
    }

    /**
     * Получает обратный ключ для дешифрования
     */
    public static Map<Character, Character> getInverseKey(Map<Character, Character> key) {
        Map<Character, Character> inverseKey = new HashMap<>();
        for (Map.Entry<Character, Character> entry : key.entrySet()) {
            inverseKey.put(entry.getValue(), entry.getKey());
        }
        return inverseKey;
    }

    /**
     * Анализирует частоты символов в тексте
     */
    public static void analyzeFrequencies(String text) {
        Map<Character, Double> frequencies = getFrequencyAnalysis(text);

        System.out.println("Частотный анализ:");
        frequencies.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> System.out.printf("%c: %.2f%%\n", entry.getKey(), entry.getValue()));
    }

    // ==================== ПРИВАТНЫЕ ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ====================

    private static String getFrequencyOrder(String language) {
        if ("sk".equalsIgnoreCase(language) || "slovak".equalsIgnoreCase(language)) {
            return SLOVAK_FREQUENCY;
        } else {
            return ENGLISH_FREQUENCY;
        }
    }

    private static Map<Character, Integer> calculateFrequencies(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerChar = Character.toLowerCase(c);
                frequencies.put(lowerChar, frequencies.getOrDefault(lowerChar, 0) + 1);
            }
        }

        return frequencies;
    }

    private static List<Character> sortByFrequency(Map<Character, Integer> frequencies) {
        return frequencies.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static Map<Character, Character> createMapping(List<Character> cipherChars, String languageChars) {
        Map<Character, Character> mapping = new HashMap<>();

        int minLength = Math.min(cipherChars.size(), languageChars.length());

        for (int i = 0; i < minLength; i++) {
            mapping.put(cipherChars.get(i), languageChars.charAt(i));
        }

        return mapping;
    }

    private static String applyMapping(String text, Map<Character, Character> mapping) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerChar = Character.toLowerCase(c);
                char mappedChar = mapping.getOrDefault(lowerChar, c);

                if (Character.isUpperCase(c)) {
                    result.append(Character.toUpperCase(mappedChar));
                } else {
                    result.append(mappedChar);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private static Map<Character, Double> getFrequencyAnalysis(String text) {
        Map<Character, Integer> frequencies = calculateFrequencies(text);
        int totalLetters = frequencies.values().stream().mapToInt(Integer::intValue).sum();

        Map<Character, Double> frequencyAnalysis = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            double frequency = (double) entry.getValue() / totalLetters * 100;
            frequencyAnalysis.put(entry.getKey(), frequency);
        }

        return frequencyAnalysis;
    }


}