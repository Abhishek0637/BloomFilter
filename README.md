#  Bloom Filter – Fast Membership Checks at Scale
Meet Bloom Filters  — a space-efficient probabilistic data structure.

## Problem
Searching large datasets is expensive.

You wait…
The system keeps loading…
And finally:
❌ **No Result Found**

This is exactly where Bloom Filters shine.

---

## What is a Bloom Filter?
A **Bloom Filter** is a probabilistic data structure used to check whether an element **might exist** in a dataset.

- ✅ Extremely fast
- ✅ Memory efficient
- ❌ Allows false positives
- ❌ Never allows false negatives

---

## Why Bloom Filters Matter
Instead of querying databases repeatedly, Bloom Filters act as a **pre-check layer**.

If Bloom Filter says:
- **NO** → item definitely does not exist
- **YES** → item *might* exist (verify from DB)

---

## How It Works
- Uses a **bit array**
- Applies **multiple hash functions**
- Each element sets multiple bits
- Lookup checks those bits

---

## Code Structure
```text
├── BloomFilter.java
└── BloomFilterDemo.java
