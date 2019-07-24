import UIKit
import Alamofire

class ProfileViewController: UIViewController {

    var accesstoken = String()
    var resourceid = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        getProfile()
    }
    func getProfile(){
        let start = resourceid.index(resourceid.startIndex, offsetBy: 9)
        let end = resourceid.index(resourceid.endIndex, offsetBy: -1)
        let range = start..<end
        let mySubstring = resourceid[range]
        let profileHeader:HTTPHeaders = [
            "authorization":"Bearer \(self.accesstoken)",
            "resourceOwnerId":String(mySubstring),
            "requestUId":"e2528114-2b9a-4a45-aff6-d56e09b2ae68",
            "accept-language":"EN"
        ]
        print(profileHeader)
        AF.request("https://api.scb.co.th/partners/sandbox/v1/customers/profile", method: .get, encoding: JSONEncoding.default, headers: profileHeader).responseJSON { (response) in
            
            switch response.result{
            case .success:
                
                do {
                    
                    print("done")
                    print(response)
                    let result = try JSONDecoder().decode(ProfileResponse.self, from: response.data!)
                    //                    self.mDataArray = result.youtubes
                    print("ok")
                    print(result)
                }catch{
                    
                }
            case .failure(let error):
                print("network error: \(error.localizedDescription)")
            }
        }
    }
}
