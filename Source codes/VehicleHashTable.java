import static java.lang.Math.*;

public class VehicleHashTable {

    private DSAHashEntry[] hashArray;

    public VehicleHashTable(int tableSize) {

        int arrSize = findNextPrime(tableSize);
        hashArray = new DSAHashEntry[arrSize];

        for (int i = 0; i < arrSize; i++) {
            DSAHashEntry hashEntry = new DSAHashEntry();
            hashArray[i] = hashEntry;
        }
    } 

    public Object[] getHashTable() {
        return hashArray;
    }

    public int getArraySize() {
        return hashArray.length;
    }

    public Object getIndexValue(int i) {
        return hashArray[i].getValue();
    }

    public Object getIndexKey(int i) {
        return hashArray[i].getKey();
    }

    public int getCount() {
        return getNumOfEntries();
    }

    private int findNextPrime(int startVal) {

        int primeVal;

        if(startVal % 2 == 0) {
            primeVal = startVal - 1;
        }
        else {
            primeVal = startVal;
        }

        boolean isPrime = false;

        do {
            primeVal += 2;
            int i = 3;
            isPrime = true;
            double rootVal = sqrt(primeVal);

            do {                
                if(primeVal % i == 0) {
                    isPrime = false;
                }
                else {
                    i += 2;
                }

            } while ((i <= rootVal) && (isPrime));

        } while(!isPrime);

        return primeVal;
    }


    public Object get(String inKey) {

        int hashIdx = hashFunction(inKey);
        int count1 = 0;
        boolean found = false, giveup = false;

        while ((!found) && (!giveup)) { 
            
            if(hashArray[hashIdx].getState() == 0) {
                giveup = true;
            }

            else if(hashArray[hashIdx].getKey().equals(inKey)) {
                found = true;
            }

            else {
                hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;

                if(count1 >= hashArray.length) {
                    giveup = true;
                }
            }

            count1++;
        }

        Object retVal = null;

        if(!found) {
            System.out.println("\n" + inKey + " was not found");
        }
        else {
            retVal = hashArray[hashIdx].getValue();
        }

        return retVal;
    }


    private Object getEntry(String inKey) {

        int hashIdx = hashFunction(inKey);
        int ogIdx = hashIdx;
        Object retVal = null;
        boolean found = false, giveup = false;

        while ((!found) && (!giveup)) { 
            
            if(hashArray[hashIdx].getState() == 0) {
                giveup = true;
            }

            else if(hashArray[hashIdx].getKey().equals(inKey)) {
                found = true;
                retVal = hashArray[hashIdx];
            }
            
            else {
                hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;
                
                if(hashIdx >= hashArray.length) {
                    giveup = true;
                }
            }
        }
        
        if(!found) {
            System.out.println("\n" + inKey + " was not found");
        }
        
        return retVal;
    }


    public boolean find(String inKey) {

        int hashIdx = hashFunction(inKey);
        boolean found = false, giveup = false;
        int attempts3 = 0;

        while ((!found) && (!giveup)) { 

            if(hashArray[hashIdx].getState() == 0) {
                giveup = true;
            }

            else if(hashArray[hashIdx].getKey().equals(inKey)) {
                found = true;
            }
            
            else {
                hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;
                attempts3++;
                
                if(attempts3 >= hashArray.length) {
                    giveup = true;
                }
            }
        }

        return found;
    }

    public void put(String inKey, Object inValue) {
        int hashIdx = hashFunction(inKey);

        if(find(inKey)) {
            DSAHashEntry duplicateEntry = (DSAHashEntry)getEntry(inKey);
            duplicateEntry.setValue(inValue);
        }
        
        else {

            if(hashArray[hashIdx].getState() != 1) {
                DSAHashEntry hashEntry = new DSAHashEntry(inKey, inValue);
                hashArray[hashIdx] = hashEntry;
            }
    
            else {
                boolean found = false;
                int attempts = 0;
                
                while (attempts <= hashArray.length && !found) {

                    hashIdx =  (hashIdx + stepHash(inKey)) % hashArray.length;

                    if(hashArray[hashIdx].getState() != 1) {
                        DSAHashEntry hashEntry = new DSAHashEntry(inKey, inValue);
                        hashArray[hashIdx] = hashEntry; 
                        found = true;
                        attempts = hashArray.length + 1;
                    }

                    attempts++;
                }
            }            
            if(getLoadFactor() >= 0.70) {
                resizeUp();
            }
        }
    }

    public void remove(String inKey) {
        
        DSAHashEntry hashEntry = (DSAHashEntry)getEntry(inKey);
        int hashIdx = hashFunction(inKey);

        if(find(inKey)) {
            
            if(hashEntry.getState() == 1 && hashArray[hashIdx].getKey().equals(inKey)) {
                DSAHashEntry hashEntry2 = new DSAHashEntry();
                hashArray[hashIdx] = hashEntry2; 
                hashArray[hashIdx].setState(-1);
                System.out.println("\nSuccessfully deleted " + hashEntry.getKey());
            }

            else {
                boolean found2 = false;
                int attempts2 = 0;
    
                while(attempts2 <= hashArray.length && !found2) {
    
                    hashIdx = (hashIdx + stepHash(inKey)) % hashArray.length;

                    if(hashArray[hashIdx].getKey().equals(inKey)) {
                        DSAHashEntry hashEntry2 = new DSAHashEntry();
                        hashArray[hashIdx] = hashEntry2; 
                        hashArray[hashIdx].setState(-1);
                        found2 = true;
                        System.out.println("\nSuccessfully deleted " + hashEntry.getKey());
                    }

                    attempts2++;
                }
            }

            while(getLoadFactor() < 0.40) {
                resizeDown();
            }
        }
    }

    public int getNumOfEntries() {
        int entries = 0;

        for(int i = 0; i < hashArray.length; i++) {
            if(hashArray[i].getState() == 1 && hashArray[i].getValue() != null) {
                entries++;
            }
        }

        return entries;
    }

    public double getLoadFactor() {
        double loadFactor = (double)getNumOfEntries() / hashArray.length;
        loadFactor = Math.round(loadFactor * 100.0) / 100.0;
        return  loadFactor; 
    }

    //FNV-1
    private int hashFunction(String key) {
        long hashIdx = 2166136261L;

        for (int i = 0; i < key.length(); i++) {
            hashIdx = (hashIdx * 16777619) ^ key.charAt(i);
        }

        hashIdx = Math.abs(hashIdx);
        return (int)(hashIdx % hashArray.length);
    }


    private int stepHash(String key) {
        int startVal = hashArray.length / 2;
        int MAX_STEP = findNextPrime(startVal);
        int hashStep = MAX_STEP - (hashFunction(key) % MAX_STEP);

        return hashStep;
    }


    private void resizeUp() {

        int newSize = findNextPrime((hashArray.length * 2));
        DSAHashEntry[] tempArray = new DSAHashEntry[hashArray.length];

        
        for (int i = 0; i < hashArray.length; i++) {
            tempArray[i] = hashArray[i];
        }
        
        hashArray = new DSAHashEntry[newSize];
        
        for (int i = 0; i < hashArray.length; i++) {
            DSAHashEntry hashEntry4 = new DSAHashEntry();
            hashArray[i] = hashEntry4;
        }
        
        for (int i = 0; i < tempArray.length; i++) {
            if(tempArray[i].getState() == 1) {
                put(tempArray[i].getKey(), tempArray[i].getValue());
            }
        }

    }


    private void resizeDown() {

        int newSize = findNextPrime((hashArray.length / 2));
        DSAHashEntry[] tempArray = new DSAHashEntry[hashArray.length];
        
        for (int i = 0; i < hashArray.length; i++) {
            tempArray[i] = hashArray[i];
        }
        
        hashArray = new DSAHashEntry[newSize];
        for (int i = 0; i < newSize; i++) {
            hashArray[i] = new DSAHashEntry();
        }
        
        for (int i = 0; i < tempArray.length; i++) {
            if(tempArray[i].getState() == 1) {
                put(tempArray[i].getKey(), tempArray[i].getValue());
            }
        }
    }


    public void display() {
        for (int i = 0; i < hashArray.length; i++) {
            if(hashArray[i].getValue() != null) {
                System.out.print("\n [" + hashArray[i].getKey() + "]");
                System.out.print(" --> Destination - " + ((Vehicle)hashArray[i].getValue()).getDestination());
                System.out.print(", Current Location - " + ((Vehicle)hashArray[i].getValue()).getLocation());
                System.out.print(", Distance - " + ((Vehicle)hashArray[i].getValue()).getDistance());
                System.out.print(", Battery Level - " + ((Vehicle)hashArray[i].getValue()).getBatteryLevel());
            }
        }
    }


    public class DSAHashEntry {

        private String key;
        private Object value;
        private int state; //0 -> never used, 1 -> used, -1 -> formerly used.

        public DSAHashEntry() {
            key = "";
            value = null;
            state = 0;
        }

        public DSAHashEntry(String inKey, Object inValue) {
            key = inKey;
            value = inValue;
            state = 1;
        }

        //setter for key.
        public void setKey(String inKey) {
            key = inKey;
        }

        //setter for value.
        public void setValue(Object inValue) {
            value = inValue;
        }

        //setter for state.
        public void setState(int inState) {
            state = inState;
        }

        //getter for key.
        public String getKey() {
            return key;
        }

        //getter for value.
        public Object getValue() {
            return value;
        }

        //getter for state.
        public int getState() {
            return state;
        }

    }


}