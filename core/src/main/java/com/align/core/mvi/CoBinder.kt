package com.align.core.mvi

typealias Action<Value> = (Value) -> Unit
typealias Binding<State> = (State?, State) -> Unit
typealias Selector<State, Value> = (state: State) -> Value

class CoBinder<State> {
    private var current: State? = null
    private val bindings: MutableList<Binding<State>> = mutableListOf()

    fun newState(state: State) {
        bindings.forEach { binding -> binding(current, state) }
        current = state
    }

    fun clear() {
        bindings.clear()
        current = null
    }

    fun <Value> on(selector: Selector<State, Value>): Selector<State, Value> = selector

    infix fun <Value> Selector<State, Value>.bind(action: Action<Value>) {
        bindings += { old, new ->
            val value = new.let(selector)

            when {
                /** State initialization */
                old == null && new != null -> action(value)
                /** New state has diff */
                value != old?.let(selector) -> action(value)
                /** Everything up-to-date */
                else -> Unit
            }
        }
    }

    infix fun <VALUE : Any> Selector<State, VALUE?>.bindNonNull(action: Action<VALUE>) {
        bindings += { old, new ->
            new.let(selector)?.let { value ->
                when {
                    /** State initialization */
                    old == null -> action(value)
                    /** New state has diff */
                    value != old.let(selector) -> action(value)
                    /** Everything up-to-date */
                    else -> Unit
                }
            }
        }
    }

    /**
     *  For readability purpose to eliminate [this] in bindings
     */
    private inline val <State, Value> Selector<State, Value>.selector: Selector<State, Value>
        get() = this
}
