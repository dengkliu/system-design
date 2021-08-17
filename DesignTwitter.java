/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
 
 // To be able to be sorted by heap, implement Comparable and override compareTo
 class Pair implements Comparable<Pair> {

     Tweet t;
     int order;

     Pair(Tweet t, int order) {
         this.t = t;
         this.order = order;
     }

     @Override
     public int compareTo(Pair p) {
         return p.order - this.order;
     }
 }

public class MiniTwitter {
    
    Map<Integer, Set<Integer>> friends;
    Map<Integer, List<Pair>> userTweets;

    private int order;

    public MiniTwitter() {
        friends = new HashMap<>();
        userTweets = new HashMap<>();
        order = 0;
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {

        Tweet newTweet = Tweet.create(user_id, tweet_text);

        order += 1;

        Pair p = new Pair(newTweet, order);
        
        List<Pair> allTweets = userTweets.getOrDefault(user_id, new ArrayList<>());
        
        allTweets.add(0, p);
        
        userTweets.put(user_id, allTweets);
        
        return newTweet;
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {

        Queue<Pair> heap = new PriorityQueue<>();

        List<Pair> selfTweetPairs = userTweets.getOrDefault(user_id, new ArrayList<>());

        int size = selfTweetPairs.size();

        for (int start = 0; start < size; start ++) {
            heap.add(selfTweetPairs.get(start));
        }

        if (friends.containsKey(user_id)) {
            for (Integer friend : friends.get(user_id)) {
                List<Pair> tweetsPair = userTweets.getOrDefault(friend, new ArrayList<>());
                for (Pair p : tweetsPair) {
                    heap.add(p);
                }
            }
        }

        int k = 0;

        List<Tweet> result = new ArrayList<>();

        while (!heap.isEmpty() && k < 10) {
            result.add(heap.poll().t);
            k ++;
        }

        return result;

    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {

        List<Tweet> result = new LinkedList<>();
        
        if (userTweets.containsKey(user_id)) {

            List<Pair> allTweetPairs = userTweets.getOrDefault(user_id, new ArrayList<>());
            
            int end = Math.min(10, allTweetPairs.size());
            
            for (int start = 0; start < end; start ++) {
                result.add(allTweetPairs.get(start).t);
            }
        }

        return result;

    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        // write your code here
        Set<Integer> followers = friends.getOrDefault(from_user_id, new HashSet<>());

        followers.add(to_user_id);

        friends.put(from_user_id, followers);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {

        Set<Integer> followers = friends.getOrDefault(from_user_id, new HashSet<>());

        if (followers.contains(to_user_id)) {
            followers.remove(to_user_id);
        }

    }
}