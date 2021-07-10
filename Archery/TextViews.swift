
// collection of template Text Views

import SwiftUI

struct HitText: View {
    var text: String
    var body: some View {
        Text(text)                              // text() method
            .bold()                             // view mod order matters
            .kerning(2.0)                       // mostly works with less mods before it
            .multilineTextAlignment(.center)    // center justified
            .lineSpacing(4.0)                   // spacing betweeen lines
            .font(.footnote)                    // see apple's typography (dynamic type sizes)
    }
}

struct BoldNumberText: View {
    var text: String
    var body: some View {
        Text(text)                              // random #, in bold from Game.swift, to aim for
            .kerning(-1.0)
            .font(.title)
            .fontWeight(.black)
            .foregroundColor(Color("TextColor"))
    }
}

struct SliderText: View {
    var text: String
    var body: some View {
        Text(text)                           
            .bold()
            .foregroundColor(Color("TextColor"))
            .frame(width: 35)
    }
}

struct LabelText: View {                            // score and round
    var text: String
    var body: some View {
        Text(text)
            .bold()
            .foregroundColor(Color("TextColor"))
            .kerning(1.5)
            .font(.caption)
    }
}

struct BodyText: View {
    var text: String
    
    var body: some View {
        Text(text)
            .font(.subheadline)
            .fontWeight(.semibold)
            .multilineTextAlignment(.center)        // center justifed
            .lineSpacing(12.0)
    }
}

struct ScoreText: View {
    var score: Int
    
    var body: some View {
        Text(String(score))
            .bold()
            .kerning(-2.0)                          // how close letters are together
            .foregroundColor(Color("TextColor"))
            .font(.title3)
    }
}

struct DateText: View {
    var date: Date
    
    var body: some View {
        Text(date,style: .time)                     // format only displays time
            .font(.subheadline)
            .fontWeight(.semibold)
            .multilineTextAlignment(.center)        // center justifed
            .lineSpacing(12.0)
    }
}

struct ButtonText: View {
    var text: String
    
    var body: some View {
        Text(text)
            .bold()
            .padding()
            .frame(maxWidth: .infinity)             // takes up all vertical space
            .background(
                Color.accentColor
            )
            .foregroundColor(.white)                // text color
            .cornerRadius(12.0)                     // rounds rect corners
    }
}

struct BoldText: View {
    let text: String
    
    var body: some View {
        Text(text)
            .kerning(2.0)
            .foregroundColor(Color("TextColor"))
            .font(.title)
            .fontWeight(.black)
    }
}

struct TextViews_Previews: PreviewProvider {
    static var previews: some View {
        VStack {                                    // wrap both in VStack so they appear together
            HitText(text: "game text")
            BoldNumberText(text: "000")
            SliderText(text: "00")
            LabelText(text: "0")
            BodyText(text: "score: 100 points")
            ButtonText(text: "new round")
            ScoreText(score: 459)
            DateText(date: Date())
            BoldText(text: "leaderboard")
        }
        .padding()                                  // take back some vertical space at edges
    }
}
