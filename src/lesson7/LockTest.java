package lesson7;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class LockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ReentrantReadWriteLock	rwLock = new ReentrantReadWriteLock();
		
		ReadLock	r = rwLock.readLock();
		try {r.lock();			// synchronized(rwLock) {
			// read-only operations
		} finally {
			r.unlock();
		}

		WriteLock	w = rwLock.writeLock();
		try {w.lock();			// synchronized(rwLock) {
			// Modifications
		} finally {
			w.unlock();
		}
	}
}
