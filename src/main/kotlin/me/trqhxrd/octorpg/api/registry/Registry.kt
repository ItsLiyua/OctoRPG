package me.trqhxrd.octorpg.api.registry

abstract class Registry<K, V : Registered<K>> {

    private val data = mutableMapOf<K, V>()

    operator fun contains(key: K) = this.data.containsKey(key)

    fun add(entry: V) {
        if (this.contains(entry.id)) throw IllegalArgumentException("The key ${entry.id} is already registered in this registry.")
        this.data[entry.id] = entry
    }

    fun getOrNull(key: K) = this.data[key]

    fun get(key: K) = this.getOrNull(key) ?: throw NullPointerException("The key $key is not present in this registry.")
}