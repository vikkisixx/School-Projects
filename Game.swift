

import Foundation

struct LeaderboardEntry {
    let score: Int
    let date: Date
}

struct Game {                                                       // Model class, handles data
    var target = Int.random(in: 3...98)                             // begins game with a random number, inclusive
    var score = 0
    var round = 1
    var leaderboardEntries: [LeaderboardEntry] = []
    
    init(loadTestData: Bool = false)                                // test data to display info before anything's entered in game
    {
        if loadTestData {
            leaderboardEntries.append( LeaderboardEntry( score: 100, date: Date() ) )
            leaderboardEntries.append( LeaderboardEntry( score: 80, date: Date() ) )
            leaderboardEntries.append( LeaderboardEntry( score: 200, date: Date() ) )
            leaderboardEntries.append( LeaderboardEntry( score: 420, date: Date() ) )
            leaderboardEntries.append( LeaderboardEntry( score: 20, date: Date() ) )
        }
    }
    
    func points(sliderValue: Int, degrees: Int) -> Int {
        
        var difference = abs( landed(sliderValue: sliderValue, degrees: degrees) - Double(target) )
        
        if difference < 3 {
            difference = 0
        }
        let earned = Int( 100.0 - difference )
        if(earned < 0) {
            return 0
        } else {
            return earned
        }
    }
    
    func landed(sliderValue: Int, degrees: Int) -> Double {
        
        let radians = ( Double(degrees) * Double.pi ) / 180         // convert to radians in parameters
        return ( ( Double(sliderValue) * Double(sliderValue) ) / 9.8 ) * ( 2.0 * cos(radians) * sin(radians) )
    }
    
    mutating func addToLeaderboard(point: Int)  {
        leaderboardEntries.append( LeaderboardEntry( score: point, date: Date() ) )
        leaderboardEntries.sort {$0.score > $1.score}
    }
    
    mutating func startNewRound(points: Int) {                      // mutating when modifying properties from struct
        score += points
        round += 1
        target = Int.random(in: 1...100)
        addToLeaderboard(point: points)
    }
    
    mutating func restart() {
        score = 0
        round = 1
        target = Int.random(in: 1...100)
        leaderboardEntries.removeAll()                              // added later, in case of issues
    }
}
