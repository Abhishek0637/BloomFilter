import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class BloomFilterDemo{

    public static void main(String[] args) {

        // Create Bloom Filter with reasonable defaults
        BloomFilter bloomFilter = new BloomFilter(1000, 3);

        // Insert known values
        bloomFilter.add("Object1");
        bloomFilter.add("Object2");
        bloomFilter.add("Object3");

        // Lookups
        System.out.println("Object1 present? " + bloomFilter.mightContain("Object1"));
        System.out.println("Object2 present? " + bloomFilter.mightContain("Object2"));
        System.out.println("Object3 present? " + bloomFilter.mightContain("Object3"));
    }
}

/**
 * Simple Bloom Filter implementation.
 *
 * Key idea:
 * - Fast membership check
 * - Memory efficient
 * - No false negatives (but false positives are possible)
 */
class BloomFilter {

    // Bit array to store hash results
    private final BitSet bitSet;

    // Size of the bit array
    private final int size;

    // Number of hash functions
    private final int hashFunctions;

    /**
     * Create a Bloom Filter
     *
     * @param size           size of bit array
     * @param hashFunctions  number of hash functions
     */
    public BloomFilter(int size, int hashFunctions) {
        this.size = size;
        this.hashFunctions = hashFunctions;
        this.bitSet = new BitSet(size);
    }

    /**
     * Add an element to the Bloom Filter
     */
    public void add(String value) {
        for (int i = 0; i < hashFunctions; i++) {
            int hash = getHash(value, i);
            bitSet.set(hash);
        }
    }

    /**
     * Check if an element might exist
     */
    public boolean mightContain(String value) {
        for (int i = 0; i < hashFunctions; i++) {
            int hash = getHash(value, i);

            // If any bit is missing, element is definitely not present
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true; // Probably present
    }

    /**
     * Generate multiple hashes using seed variation
     */
    private int getHash(String value, int seed) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        int hash = 0;

        for (byte b : bytes) {
            hash = (hash * 31) ^ b ^ seed;
        }

        // Ensure index stays inside bitset range
        return Math.abs(hash % size);
    }
}

// 💡 Suggestions to Improve This Bloom Filter :

// Use better hash functions

    // MurmurHash

    // FNV-1a

    // Guava’s Hashing.murmur3_128()

// Auto-calculate size

    // m = -(n * ln(p)) / (ln(2)^2)
    // k = (m / n) * ln(2)


// Where:

    // n = expected elements

    // p = false positive probability



// Production usage

        // Redis Bloom

        // Google Guava BloomFilter

        // Apache Cassandra internals

// Do NOT use when

    // Deletions are required (use Counting Bloom Filter instead)