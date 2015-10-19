package fr.mines.entitites;

public interface MergeableEntity<T> {
	public void copyIn(T other);
}
