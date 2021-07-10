//
//  LeaderboardView.swift
//  Bullseye
//
//  Created by Victor Sanchez on 6/29/21.
//

import SwiftUI

struct LeaderboardView: View {
    @Binding var leaderboardIsShowing: Bool
    @Binding var game: Game
    
    var body: some View {
        ZStack {
            VStack (spacing: 10) {
                HeaderView(leaderboardIsShowing: $leaderboardIsShowing)
                LabelView()
                ScrollView {                                                // scroll leaderboard
                    VStack(spacing: 10) {
                        ForEach(game.leaderboardEntries.indices) { i in
                            let leaderboardEntry = game.leaderboardEntries[i]
                            RowView(index: i, score: leaderboardEntry.score, date: leaderboardEntry.date)
                        }
                    }
                }
            }
        }
    }
}

struct HeaderView: View {
    @Binding var leaderboardIsShowing: Bool
    @Environment(\.verticalSizeClass) var verticalSizeClass
    @Environment(\.horizontalSizeClass) var horizontalSizeClass
    
    var body: some View {
        ZStack {
            HStack {                                                        // compact/regular sizes based on orientation
                if verticalSizeClass == .regular && horizontalSizeClass == .compact {
                    BoldText(text: "leaderboard")
                        .padding(.leading)
                    Spacer()
                } else {
                    BoldText(text: "leaderboard")
                }
                
            }
            .padding(.top)
            HStack {
                Spacer()                                            // right justifies next element
                Button(action: {leaderboardIsShowing = false}) {
                    RoundedImageViewFilled(systemName: "xmark")     // x button
                        .padding(.trailing)
                }
            }
        }
    }
}

struct LabelView: View {
    
    var body: some View {
        HStack {
            Spacer()
                .frame(width: Constants.General.roundedViewLength)
            Spacer()
            LabelText(text: "score")
                .frame(width: Constants.Leaderboard.leaderboardScoreColWidth)
            Spacer()
            LabelText(text: "date")
                .frame(width: Constants.Leaderboard.leaderboardDateColWidth)
        }
        .padding(.leading)                                                  // makes space from the left
        .padding(.trailing)                                                 // make space from the right
        .frame(maxWidth: Constants.Leaderboard.leaderboardMaxRowWidth)
    }
}

struct RowView: View {
    let index: Int
    let score: Int
    let date: Date                                                          // Date type variable
    
    var body: some View{
        HStack {
            RoundedTextView(text: String(index))
            Spacer()                                                        // for landscape
            ScoreText(score: score)
                .frame(width: Constants.Leaderboard.leaderboardScoreColWidth)
            Spacer()                                                        // for landscape
            DateText(date: date)
                .frame(width: Constants.Leaderboard.leaderboardDateColWidth)
        }
        .background(
            RoundedRectangle(cornerRadius: .infinity)
                .strokeBorder(Color.black, lineWidth: Constants.General.strokeWidth)
        )
        .padding(.leading)                                                  // makes space from the left
        .padding(.trailing)                                                 // make space from the right
        .frame(maxWidth: Constants.Leaderboard.leaderboardMaxRowWidth)      // fixed width for landscape
    }
}

struct LeaderboardView_Previews: PreviewProvider {
    static private var leaderboardIsShowing = Binding.constant(false)
    static private var game = Binding.constant(Game(loadTestData: true))
    
    static var previews: some View {
        LeaderboardView(leaderboardIsShowing: leaderboardIsShowing, game: game)                                           // portait orientation
    }
}
