package com.csci5308.stocki5.stock.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class Subject
{
	private final List<IObserver> observers;

	public Subject()
	{
		observers = new ArrayList<>();
	}

	public void attach(IObserver observer)
	{
		observers.add(observer);
	}

	public void detach(IObserver observer)
	{
		observers.remove(observer);
	}

	public void notifyObservers()
	{
		ListIterator<IObserver> iter = observers.listIterator();
		while (iter.hasNext())
		{
			iter.next().update();
		}
	}

}
